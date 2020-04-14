#include "Complex.h"

Complex::Complex(): real(0), img(0) {}

Complex::Complex(double r, double i): real(r), img(i) {}

Complex::~Complex() {}

double Complex::getReal() const {
    return real;
}


double Complex::getImg() const {
    return img;
}
