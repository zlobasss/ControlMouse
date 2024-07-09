#ifndef VM_H
#define VM_H
#include <libevdev/libevdev-uinput.h>
#include <mutex>

class VirtualMouse
{
public:
    struct libevdev_uinput* m_uinput {};

public:
    VirtualMouse();
    VirtualMouse(const VirtualMouse& other);
    ~VirtualMouse();
    int Init(int mouse_id);
    void Set(int x, int y);
    void MoveMouseInCircle(int centerX, int centerY, int radius);
};

int initVectorVm();

int moveAll();

#endif // VM_H