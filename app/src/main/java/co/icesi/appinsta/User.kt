package co.icesi.appinsta

class User {
    var username: String
    var email: String
    var password: String

    constructor(username: String, email: String, password: String){
        this.username = username
        this.email = email
        this.password = password
    }
}