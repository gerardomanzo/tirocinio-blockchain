<%@page import="bean.Utente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Dashboard utente</title>

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
                <nav class="navbar">

                    <%   Utente utente = (Utente) session.getAttribute("utente");
                        if (utente != null) {
                    %>
                    <a href="Logout" style="color: white"> <i class="fa fa-power-off fa-3x float-xs-right"></i> </a>

                    <% }%>

                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->
            <section>
                <div class="container">
                    <h1 class="text-center">Benvenuto <%=utente.getNomeUtente()%>!</h1>
                    <h3>Operazioni disponibili</h3>
                    <%
                        if (utente != null) {
                            if (utente.getStatoRegistrazione()) {
                    %>


                    <div class="container">
                        <div class="card-deck">
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="registrazioneOggetto.jsp" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Registra nuovo Oggetto</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Registra il tuo Oggetto.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="PartecipazioneServlet" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Registra Oggetto ad un Evento</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Per partecipare ad un Evento devi prima iscrivere un tuo Oggetto.</p>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header bg-blue">
                                    <a href="" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Vota!</a>
                                </div>
                                <div class="card-body">
                                    <p class="card-text">Vota gli Oggetti degli altri Utenti.</p>
                                </div>
                            </div>
                        </div>

                    </div>
                    <% } else { %>
                    <p>Per effettuare operazioni la tua registrazione deve essere confermata dall'amministratore</p>
                    <% }%>
                </div>
            </section>
            <% } else {
            %>
            <section>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-4 offset-lg-4 col-md-8 offset-md-2">
                            <div class="form">
                                <form method="POST" action="LoginUtente">
                                    <h1>
                                        <i class="fa fa-user-circle"></i> Login utente
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