#include "vm.h"
#include <libevdev/libevdev.h>
#include <libevdev/libevdev-uinput.h>
#include <mutex>
#include <thread>
#include <chrono>
#include <iostream>
#include <cmath>
#include <iomanip>
#include <vector>

std::mutex m_mouseMutex;

std::vector<VirtualMouse> vms;

VirtualMouse::VirtualMouse(const VirtualMouse& other)
{
    m_uinput = nullptr;
}

VirtualMouse::VirtualMouse()
{
    m_uinput = nullptr;
}

VirtualMouse::~VirtualMouse()
{
    libevdev_uinput_destroy(m_uinput);
}

int VirtualMouse::Init(int mouseId)
{
    m_uinput = std::unique_ptr();
    std::string name = "Virtual Mouse " + std::to_string(mouseId);
    struct libevdev* dev = libevdev_new();
    libevdev_set_name(dev, name.c_str());
    std::cout << name << std::endl;

    libevdev_enable_property(dev, INPUT_PROP_POINTER);

    libevdev_enable_event_type(dev, EV_ABS);
    libevdev_enable_event_code(dev, EV_ABS, ABS_X, nullptr);
    libevdev_enable_event_code(dev, EV_ABS, ABS_Y, nullptr);

    int r = libevdev_uinput_create_from_device(dev, LIBEVDEV_UINPUT_OPEN_MANAGED, &m_uinput);

    libevdev_free(dev);

    return r;
}

void VirtualMouse::Set(int x, int y)
{
    std::lock_guard<std::mutex> guard(m_mouseMutex);
    libevdev_uinput_write_event(m_uinput, EV_ABS, ABS_X, x);
    libevdev_uinput_write_event(m_uinput, EV_ABS, ABS_Y, y);
    libevdev_uinput_write_event(m_uinput, EV_SYN, SYN_REPORT, 0);
}

void VirtualMouse::MoveMouseInCircle(int centerX, int centerY, int radius)
{
    int fps = 60;
    int durationSleep = 1000 / fps; // 1000ms / fps
    int steps = 360;
    for (int i = 0; i < steps; ++i)
    {
        int x = centerX + radius * cos(i * 2 * M_PI / steps);
        int y = centerY + radius * sin(i * 2 * M_PI / steps);
        auto now = std::chrono::system_clock::now();
        std::time_t now_time_t = std::chrono::system_clock::to_time_t(now);
        auto now_tm = *std::localtime(&now_time_t);
        std::cout << std::put_time(&now_tm, "%Y-%m-%d %H:%M:%S");
        Set(x, y);

        std::this_thread::sleep_for(std::chrono::milliseconds(durationSleep));
    }
}

int initVectorVm()
{
    int count = 3;
    for (int i = 0; i < count; ++i)
    {
        std::cout << "add" << std::endl;
        vms.push_back(VirtualMouse());
        int res = vms[i].Init(i);
        if (res != 0)
        {
            return res;
        }
    }
    return 0;
}
