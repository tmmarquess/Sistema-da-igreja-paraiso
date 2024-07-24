function verificaCampos() {
    var nome = document.getElementById("nome").value
    var telefone = document.getElementById("tel").value
    var email = document.getElementById("email").value
    var senha = document.getElementById("senha").value
    var dataN = document.getElementById("data").value
    var cep = document.getElementById("cep").value
    var numero = document.getElementById("numero").value

    if(nome == '' || telefone == '' || email == '' || senha == '' || dataN == '' || cep == '' || numero == ''){
        window.alert("Verifique os dados e tente novamente")
        return false
    }else{
        return true
    }
}