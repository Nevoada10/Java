# A program that gets input from the user and uses the Lorentz formula to calculate the ideal body weight.

# Author: Firstname Lastname Silva (https://gitlab.com/a253119un)

# Importing libraries
import math

# Main function
def main():

    DEBUG = True
    
    if DEBUG:
        # For testing
        height = 184
        age = 24
        gender = 2
    else:
        # Getting input from the user
        height = float(input("Enter your height in cm: "))
        age = int(input("Enter your age: "))
        gender = int(input("Enter your gender (1 for man and 2 for woman): "))

    # Using the Lorentz formula to calculate the ideal body weight
    k = 4 if gender == 1 else 2.5
    lorentzWeight = (height - 100) - ((height - 150) / 4) + ((age - 20)/k)

    print("Your ideal body weight is: ", round(lorentzWeight, 2))


main()

