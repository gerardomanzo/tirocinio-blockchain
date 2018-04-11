<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Dashboard admin</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">

        <!-- Font Awesome CSS -->
        <link rel="stylesheet" type="text/css" href="./font-awesome/css/font-awesome.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="./css/style.css">
    </head>
    <body>

        <!-- Page -->
        <div id="page-content-wrapper">

            <!-- Header -->
            <header>
                <nav class="navbar" style="min-height: 50px">

                    <%
                        Boolean login = (Boolean) session.getAttribute("loginAdmin");
                        if (login != null && login.booleanValue()) {
                    %>
                    <a href="Logout" style="color: white"> <i class="fa fa-power-off fa-3x float-xs-right"></i> </a>
                    <% }  %>

                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->
            <%
                if (login != null && login.booleanValue()) {
            %>
            <section>
                <div class="container">
                    <h1 class="text-center">Benvenuto amministratore!</h1>
                    <h3>Operazioni disponibili</h3>

                    <div class="container">
                        <div class="card-deck">
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="creaVotazione.jsp" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Crea nuova Votazione</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Crea una nuova Votazione.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="creaCriterio.jsp" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Crea nuovo Criterio</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Crea un nuovo Criterio di valutazione.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="AssociaCriterio" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Associa Criterio a Votazione</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Associa un Criterio di valutazione ad una Votazione.</p>
                                </div>
                            </div>
                        </div>
                        <div class="card-deck" style="margin-top: 1rem;">
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="ConfermaUtente" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Conferma registrazione Utente</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Conferma la registrazione di un Utente.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="ConfermaCandidatura" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Conferma registrazione Candidatura</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Conferma la registrazione di una Candidatura.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="ConfermaPartecipazione" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>Conferma partecipazione a Votazione</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Conferma la partecipazione di una Candidatura ad una Votazione.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>				
            <%
            } else {
            %>
            <section>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4 offset-lg-4 col-md-8 offset-md-2">
                            <div class="form">
                                <form method="POST" action="LoginAdmin">
                                    <h1>
                                        <i class="fa fa-user-circle"></i> Login admin
                                    </h1>
                                    <input id="email" type="text" placeholder="Email" name="email"/>
                                    <input id="password" type="password" placeholder="Password" name="password"/>
                                    <button>Entra!</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%
                }
            %>
            <!-- /Main -->

            <!-- Footer -->
            <footer> </footer>
            <!-- /Footer -->

        </div>
        <!-- /Page -->

    </div>

    <script src="./js/jquery.js"></script>
    <script src="./js/popper.js"></script>
    <script src="./js/bootstrap.js"></script>

</body>
</html>