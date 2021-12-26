
	setTimeout(()=>{
		
		$("#msgEditado").css({
			"display":"none"
		})
		
		$("#msgErro").css({
			"display":"none"
		})
		
		$("#msgExcluido").css({
			"display":"none"
		})
		
		$("#msgCadastrado").css({
			"display":"none"
		})
		
	}, 5000)
	
	
	// faz clicar no botao que chama um servlet que reinicia as variaveis de
	// pessoa.
	$(".btn-close").click(()=>{
		
		if($("#reinicia").val() == "sim"){
			$("#reiniciaVariaveis").click();	
		}
		
		 $("#erroNomeCadastro").css({
             "display": "none"
         })

		$("#erroNomeEdicao").css({
             "display": "none"
         })

		$("#erroCpfCadastro").css({
             "display": "none"
         })	

		$("#erroCpfEdicao").css({
             "display": "none"
         })	

		$("#erroEmailCadastro").css({
             "display": "none"
        })

		$("#erroEmailEdicao").css({
             "display": "none"
        })

		$("#erroEmailConfirmCadastro").css({
              "display": "none"
        })

		$("#erroEmailConfirmEdicao").css({
              "display": "none"
        })

		$("#nomeCadastro").val("");
		$("#nomeEdicao").val("");
		$("#cpfCadastro").val("");
		$("#cpfEdicao").val("");
		$("#emailCadastro").val("");
		$("#emailEdicao").val("");
		$("#emailConfirmCadastro").val("");
		$("#emailConfirmEdicao").val("");
	})
	
	///////////////////////////////////

	// verifica se teve uma edicao ou algum cadastro 
	// que não obteve exito
	function verifica(){
		let pc = $("#pc");
	
		if(pc.val() == "simC"){
			$('#abrirModalCadastro').click();
		}
		
		let pe = $("#pe");
		
		if(pe.val() == "simE"){
			$('#abrirModalEditar').click();
		}	
	}
	/////////////////////////////////////
	
	// tratando nome cadastro
	$("#nomeCadastro").change(()=>{
	
	
        let nome = $("#nomeCadastro");

        if (nome.val().trim().length < 4) {
            $("#erroNomeCadastro").css({
                "display": "block"
            })

            nome.val("");

        } else {

            // nao pode conter letras
            let regex = /[^A-Za-z ]/;
            
            if (regex.test(nome.val().trim())) {

                $("#erroNomeCadastro").css({
                    "display": "block"
                })
    
                nome.val("");

            } else {

                $("#erroNomeCadastro").css({
                    "display": "none"
                })

            }
        }
    })
    ////////////////////////////

	// tratando nome edicao
	$("#nomeEdicao").change(()=>{
	
	
        let nome = $("#nomeEdicao");

        if (nome.val().trim().length < 4) {
            $("#erroNomeEdicao").css({
                "display": "block"
            })

            nome.val("");

        } else {

            // nao pode conter letras
            let regex = /[^A-Za-z ]/;
            
            if (regex.test(nome.val().trim())) {

                $("#erroNomeEdicao").css({
                    "display": "block"
                })
    
                nome.val("");

            } else {

                $("#erroNomeEdicao").css({
                    "display": "none"
                })

            }
        }
    })
    ////////////////////////////

	// tratando cpf cadastro
    $("#cpfCadastro").change(()=>{

        let cpf = $("#cpfCadastro");

        let regex = /\d{3}\.\d{3}\.\d{3}\-\d{2}$/;

        if (!regex.test(cpf.val().trim())) {

            $("#erroCpfCadastro").css({
                "display": "block"
            })

            cpf.val("");

        } else {

            $("#erroCpfCadastro").css({
                "display": "none"
            })

        }
    })
    ////////////////////////////

	// tratando cpf cadastro
    $("#cpfEdicao").change(()=>{

        let cpf = $("#cpfEdicao");

        let regex = /\d{3}\.\d{3}\.\d{3}\-\d{2}$/;

        if (!regex.test(cpf.val().trim())) {

            $("#erroCpfEdicao").css({
                "display": "block"
            })

            cpf.val("");

        } else {

            $("#erroCpfEdicao").css({
                "display": "none"
            })

        }
    })
    ////////////////////////////

	// tratando email cadastro
    $("#emailCadastro").change(() =>{

        let email = $("#emailCadastro");

        let regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if (!regex.test(email.val().trim())) {

            $("#erroEmailCadastro").css({
                "display": "block"
            })

            email.val("");

        } else {

            $("#erroEmailCadastro").css({
                "display": "none"
            })

        }
    })
    ////////////////////////////

	// tratando email edicao
    $("#emailEdicao").change(() =>{

        let email = $("#emailEdicao");

        let regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

        if (!regex.test(email.val().trim())) {

            $("#erroEmailEdicao").css({
                "display": "block"
            })

            email.val("");

        } else {

            $("#erroEmailEdicao").css({
                "display": "none"
            })

        }
    })
    ////////////////////////////

	// tratando confirmação de email cadastro
	$("#emailConfirmCadastro").change(() =>{

        let email = $("#emailCadastro");
        let emailConfirm = $("#emailConfirmCadastro");

        if (emailConfirm.val().trim() !== email.val().trim()) {

            $("#erroEmailConfirmCadastro").css({
                "display": "block"
            })

            emailConfirm.val("");

        } else {

            $("#erroEmailConfirmCadastro").css({
                "display": "none"
            })

        }

    })
    ////////////////////////////

	// tratando confirmação de email edicao
	$("#emailConfirmEdicao").change(() =>{

        let email = $("#emailEdicao");
        let emailConfirm = $("#emailConfirmEdicao");

        if (emailConfirm.val().trim() !== email.val().trim()) {

            $("#erroEmailConfirmEdicao").css({
                "display": "block"
            })

            emailConfirm.val("");

        } else {

            $("#erroEmailConfirmEdicao").css({
                "display": "none"
            })

        }

    })
    ////////////////////////////



	// funcao usada para levar o id da tabela ate o formulario de edição
	function pegarIdEditar(id) {
			
	    $("#formularioEditar").find("#id").val(id);
	    $("#formularioEditar").find(".mb-3 > #nomeEdicao").val($(`#${id}`).find('td[data-nome]').data('nome'));
	    $("#formularioEditar").find(".mb-3 > #cpfEdicao").val($(`#${id}`).find('td[data-cpf]').data('cpf'));
	    $("#formularioEditar").find(".mb-3 > .col-md-6 > #emailEdicao").val($(`#${id}`).find('td[data-email]').data('email'));
	
	    $("#abrirModalEditar").click();
	
	}
	////////////////////////////

	// funcao usada para levar o id ate o modal de exclusao
	function pegarIdExcluir(id) {
	
	    $("#formularioExcluir").find("#id").val(id);
	
	    $("#abrirModalExcluir").click(); 
	
	}
	//////////////////////////// 

 