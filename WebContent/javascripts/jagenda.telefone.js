var clickEnviar = function()
{
	//construir objeto
	var obj = {
			tipoFone: $("[name=tipoFone]").val(),
			ddd: $("[name=ddd]").val(),
			numeroFone: $("[name=numeroFone]").val()
	};
	var form = $("#formFone").serialize();
	//enviar objeto

	$.ajax({
		type: "get",
		url: "<c:url value='/agenda/form/addFone/'/>" + $("[name=id]").val(),
		data: obj
	}).done(function(dado, v1, v2){
		if(typeof(dado) == "Array")
		{
			alert("Deu Certo");
		}
		else
		{
			alert("Deu Errado");
		}
	}).fail( function(dado, v1, v2){
		alert("Quebrou!!!");
	});
};

$(document).ready(function(){
	$("[name=Botao]").click(function(){
		
		clickEnviar();
	})
})