cmake_minimum_required(VERSION 3.10)

project("MouseMoveCircle")
set(CMAKE_CXX_STANDART 11)

include_directories(include)

add_executable(mouse src/main.cpp src/vm.cpp headers/vm.h)

find_package(PkgConfig REQUIRED)
pkg_check_modules(LIBEVDEV REQUIRED libevdev)

# Добавьте включаемые директории и линковочные библиотеки
target_include_directories(mouse PRIVATE ${LIBEVDEV_INCLUDE_DIRS} ${PROJECT_SOURCE_DIR}/headers)
target_link_libraries(mouse PRIVATE ${LIBEVDEV_LIBRARIES})