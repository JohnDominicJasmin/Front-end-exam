package com.example.codingexamui.domain.use_case

import com.example.codingexamui.domain.exception.PasswordValidationException
import com.example.codingexamui.domain.exception.UsernameValidationException
import com.example.codingexamui.domain.model.ValidationResult

class LoginUseCase {
    operator fun invoke(userName: String, password: String): ValidationResult {
        if (userName.isBlank()) {
            throw UsernameValidationException("Username cannot be empty")
        }

        if (password.isBlank()) {
            throw PasswordValidationException("Password cannot be empty")
        }

        if (userName.length < 3) {
            throw UsernameValidationException("Username must be at least 3 characters long")
        }

        if (password.length < 8) {
            throw PasswordValidationException("Password must be at least 8 characters long")
        }

        if (!password.any { it.isDigit() }) {
            throw PasswordValidationException("Password must contain at least one digit")
        }

        if (!password.any { it.isUpperCase() }) {
            throw PasswordValidationException("Password must contain at least one uppercase letter")
        }



        if (userName != "admin") {
            throw UsernameValidationException("Username is incorrect")
        }
        if (password != "Admin1234") {
            throw PasswordValidationException("Password is incorrect")
        }

        return ValidationResult(true, "Login successful")


    }
}