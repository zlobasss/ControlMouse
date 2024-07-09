#include <iostream>
#include <string>
#include "vm.h"

void startMove(int x, int y)
{

}

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        return -1;
    }

    int a = 0;
    std::string command = argv[1];

    if (command == "start" && argc == 5)
    {
        int x, y, frequency;
        try
        {
            x = std::stoi(argv[2]);
            y = std::stoi(argv[3]);
            frequency = std::stoi(argv[4]);
        }
        catch (std::invalid_argument e)
        {
            std::cout << "Invalid arguments" << std::endl;
            return -2;
        }
        std::cout << x << ' ' << y << ' ' << frequency << std::endl;
        if (initVectorVm() != 0)
        {
            return -3;
        }
        while (true) {}
    }
    return 0;
}