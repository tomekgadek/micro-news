package com.micronews.identity.domain

import groovy.transform.CompileStatic

@CompileStatic
trait SampleUsers {

    User userKowalski = new User(1, "Jan", "Kowalski", "Warszawa")
    
    // pass is BCrypt hash for "password123"
    Login loginKowalski = new Login(
            "jkowalski", 
            "\$2b\$12\$MdPP6Uv45QbtfkMnRE3uSuyov8VVpATUHHTMu4HGdu9GCWED/ILVC", 
            1, 
            "USER"
    )
}
