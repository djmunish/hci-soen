#ifndef __CCOMPLEX_H
#define __CCOMPLEX_H

class Complex{
private:
    double real, img;
public:
    Complex();
    Complex(double, double = 0);
    double getReal() const;
    double getImg() const;
    ~Complex();

};

#endif
