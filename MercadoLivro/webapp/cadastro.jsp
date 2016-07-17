<html dir="ltr" lang="pt-br">
   <!--<![endif]-->
   <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Mercado Livro - Cadastro</title>
      <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
      <link href="css/lightbox.css" rel="stylesheet">
      <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
      <link href="//fonts.googleapis.com/css?family=Open+Sans:400,400i,300,700" rel="stylesheet" type="text/css">
      <link href="stylesheet/stylesheet.css" rel="stylesheet">
      <!-- Bootstrap core CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" href="css/font-awesome.min.css">
      <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,300italic,400italic,500italic" rel="stylesheet" type="text/css">
      <!-- Custom styles for this template -->
      <link href="stylesheet/style.css" rel="stylesheet">
      <link href="stylesheet/legion.css" rel="stylesheet">
      <link href="jquery-ui.css" type="text/css" rel="stylesheet" media="screen">
      <link href="owl.carousel.css" type="text/css" rel="stylesheet" media="screen">
   </head>
   <body class="common-home">
      <nav id="menuTop" class="navbar navbar-inverse navbar-static-top pattern-bottom">
         <div class="container">
            <div id="navbar" class="collapse navbar-collapse">
               <ul class="nav navbar-nav navbar-right">
               <li style="color:yellow; display:${(logged=='true')?'yes':'none'}" >(Atualmente logado como ${usuarioLogado.getNome()})</li>
               </ul>
            </div>
         </div>
      </nav>
      <header class="jumbotron">
         <br>
         <div class="container">
            <div class="row">
               <div class="col-xs-12 col-sm-5 text-center">
               </div>
               <div class="col-xs-12 col-sm-6 col-sm-offset-1 header-items">
                  <ul class="list-unstyled">
                     <li>
                        <div id="search" class="input-group">
                           <input type="text" name="search" value="" placeholder="Entre com a busca..." class="form-control input-lg ui-autocomplete-input" autocomplete="off">
                           <span class="input-group-btn">
                           <button type="button" class="btn btn-default btn-lg"><i class="fa fa-search"></i></button>
                           </span>
                        </div>
                     </li>
                  </ul>
               </div>
            </div>
         </div>
         <br>
      </header>
      <nav id="menuMiddle" class="navbar navbar-inverse navbar-static pattern-top-bottom navbar-background-double">
      </nav>
      <div class="container">
         <div class="row">
            <div class="col-xs-12 col-sm-3">
               <div id="navigation">
                  <ul class="list-group list-group-inverse" id="menu" role="navigation">
                     <li class="list-group-item collapsable">
                        <a href="livros">Livros</a>
                     </li>
                     <li class="list-group-item collapsable">
                        <a href="cadernos.jsp">Cadernos</a>
                     </li>
                     <li class="list-group-item collapsable">
                        <a href="presentes.jsp">Presentes</a>
                     </li>
                  </ul>
               </div>
            </div>
            <div id="content" class="col-xs-12 col-sm-4">
               <div class="cadastro">
                     <style type="text/css">
                     </style>
                     <form>
                     <fieldset>
                     <legend>Sou novo aqui</legend>
                        E-mail<br>
                        <input class= "cadastro-input" type="email" name="email"><br>
                        Nome<br>
                        <input class= "cadastro-input" type="text" name="nome"><br>
                        Senha<br>
                        <input class= "cadastro-input" type="password" name="senha"><br>
                        Confirmar senha<br>
                        <input class= "cadastro-input" type="password" name="senhaReafirm"><br><br>
                        <button class= "cadastro-submit" type="submit" value="cadastrar" name="operacao">Cadastrar</button>
                     </fieldset>
                     </form>
              </div>
            </div>
            <div id="content" class="col-xs-12 col-sm-4">
               <div class="cadastro">
                     <form>
                     <fieldset>
                     <legend>Já possuo uma conta</legend>
                        E-mail<br>
                        <input class= "cadastro-input" type="email" name="email"><br>
                        Senha<br>
                        <input class= "cadastro-input" type="password" name="senha"><br><br>
                        <button class= "cadastro-submit" type="submit" value="login" name="operacao">Login</button>
                     </fieldset>
                     </form>
              </div>
            </div>
            ${msg}
         </div>
      </div>

      <footer>
         <div class="container">
            <div class="row" style="border-top:1px solid white;padding-top:10px;margin-top:10px;">
               <div class="col-sm-12">
                  © Trabalho de PW | <small>dev by <strong>guilhermeCoutinho, enioMoura e douglasPfeiffer</strong></small>
               </div>
            </div>
            <div class="row">
               <div class="col-sm-12">
                  &nbsp;
               </div>
            </div>
         </div>
      </footer>
   </body>
</html>