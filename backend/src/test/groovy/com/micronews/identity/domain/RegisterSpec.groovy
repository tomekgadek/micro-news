package com.micronews.identity.domain

import com.micronews.identity.dto.LoginRequest
import com.micronews.identity.dto.RegisterRequest
import com.micronews.identity.dto.UserDto
import com.micronews.identity.dto.UserAlreadyExistsException
import com.micronews.identity.dto.InvalidRegistrationDataException
import spock.lang.Specification

class RegisterSpec extends Specification implements SampleUsers {

    IdentityFacade facade = new IdentityConfiguration().identityFacade()

    def setup() {
        facade.userRepository.save(userKowalski)
        facade.loginRepository.save(loginKowalski)
    }

    def "should register successfully with valid credentials and complex password"() {
        given: "a registration request with a strong password"
        def request = new RegisterRequest("newuser", "StrongPass123!", "Adam", "Nowak", "Krakow")

        when: "we register the user"
        def response = facade.register(request)

        then: "registration succeeds and returns a UserDto"
        response != null
        response.id() != null
        response.firstname() == "Adam"
        response.surname() == "Nowak"
        response.city() == "Krakow"

        and: "the user can log in with the new credentials"
        def loginResponse = facade.login(new LoginRequest("newuser", "StrongPass123!"))
        loginResponse != null
        loginResponse.token() != null
    }

    def "should fail to register if user already exists"() {
        given: "a registration request with an already taken login"
        def request = new RegisterRequest("jkowalski", "StrongPass123!", "Jan", "Kowalski", "Warszawa")

        when: "we register the user"
        facade.register(request)

        then: "a UserAlreadyExistsException is thrown"
        thrown(UserAlreadyExistsException)
    }

    def "should fail to register with blank fields"() {
        given: "a registration request with blank login or password"
        def request = new RegisterRequest(login, password, "Jan", "Kowalski", "Warszawa")

        when: "we register the user"
        facade.register(request)

        then: "an InvalidRegistrationDataException is thrown"
        thrown(InvalidRegistrationDataException)

        where:
        login     | password
        ""        | "StrongPass123!"
        null      | "StrongPass123!"
        "newuser" | ""
        "newuser" | null
    }

    def "should fail to register with a weak password"() {
        given: "a registration request with a weak password"
        def request = new RegisterRequest("newuser", password, "Jan", "Kowalski", "Warszawa")

        when: "we register the user"
        facade.register(request)

        then: "an InvalidRegistrationDataException is thrown"
        thrown(InvalidRegistrationDataException)

        where:
        password          | description
        "Short1!"         | "too short"
        "lowercase123!"   | "missing uppercase"
        "UPPERCASE123!"   | "missing lowercase"
        "NoDigitsHere!"   | "missing digit"
        "NoSpecialChar12" | "missing special character"
    }
}
