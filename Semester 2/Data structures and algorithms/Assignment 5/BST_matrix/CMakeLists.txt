cmake_minimum_required(VERSION 3.21)
project(BST_matrix)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(BST_matrix
        App.cpp
        ExtendedTest.cpp
        ExtendedTest.h
        Matrix.cpp
        Matrix.h
        ShortTest.cpp
        ShortTest.h MatrixIterator.cpp MatrixIterator.h)
