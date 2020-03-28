//
// Created by xiahangli on 2020-03-28.
//
#include "TutorialConfig.h"
#include <cstdlib>
#include <iostream>
#include <TutorialConfig.h>

class TutorialConfig {
public:
    void print() {
        if (true) {
                // report version
            std::cout << "argv[0]" << " Version " << Tutorial_VERSION_MAJOR << "."
                      << Tutorial_VERSION_MINOR << std::endl;
            std::cout << "Usage: " << "argv[0]" << " number" << std::endl;
//            return 1;
        }
    }
};
