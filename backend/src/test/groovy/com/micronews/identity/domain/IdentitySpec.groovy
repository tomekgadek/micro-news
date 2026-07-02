package com.micronews.identity.domain

import com.micronews.identity.dto.LoginRequest
import com.micronews.identity.dto.LoginResponse
import com.micronews.identity.dto.InvalidCredentialsException
import spock.lang.Specification

class IdentitySpec extends Specification implements SampleUsers {

    IdentityFacade facade = new IdentityConfiguration().identityFacade()

    def setup() {
        facade.userRepository.save(userKowalski)
        facade.loginRepository.save(loginKowalski)
    }

    def "should login successfully with valid credentials"() {
        given: "a login request with correct username and password"
        def request = new LoginRequest("jkowalski", "password123")

        when: "we attempt to login"
        def response = facade.login(request)

        then: "login succeeds and returns a JWT token"
        response != null
        response.token() != null
        response.token().length() > 0
    }

    def "should fail to login if user does not exist"() {
        given: "a login request with non-existent username"
        def request = new LoginRequest("nonexistent", "password123")

        when: "we attempt to login"
        facade.login(request)

        then: "an InvalidCredentialsException is thrown"
        thrown(InvalidCredentialsException)
    }

    def "should fail to login if password does not match"() {
        given: "a login request with incorrect password"
        def request = new LoginRequest("jkowalski", "wrongpassword")

        when: "we attempt to login"
        facade.login(request)

        then: "an InvalidCredentialsException is thrown"
        thrown(InvalidCredentialsException)
    }
}
