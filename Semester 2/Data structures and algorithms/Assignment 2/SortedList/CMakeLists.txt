cmake_minimum_required(VERSION 3.21)
project(SortedList)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(SortedList
        App.cpp
        ExtendedTest.cpp
        ExtendedTest.h
        ListIterator.cpp
        ListIterator.h
        ShortTest.cpp
        ShortTest.h
        SortedIteratedList.cpp
        SortedIteratedList.h)
