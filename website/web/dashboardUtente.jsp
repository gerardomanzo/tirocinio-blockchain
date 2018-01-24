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

        <div id="wrapper">

            <!-- SideBar -->
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li><a href="#">Item 1</a></li>
                    <li><a href="#">Item 2</a></li>
                    <li><a href="#">Item 3</a></li>
                    <li><a href="#">Item 4</a></li>
                </ul>
            </div>
            <!-- /Sidebar -->

            <!-- Page -->
            <div id="page-content-wrapper">

                <!-- Header -->
                <header>
                    <nav class="navbar navbar-expand-lg">
                        <a href="#menu-toggle" class="btn btn-primary" id="menu-toggle">
                            <i class="fa fa-bars"></i>
                        </a>
                    </nav>
                </header>
                <!-- /Header -->

                <!-- Main -->
                <%
                    Utente utente = (Utente) session.getAttribute("utente");
                    if (utente != null) {
                %>
                <section>
                    <div class="container">
                        <h1 class="text-center">Benvenuto <%=utente.getNomeUtente()%>!</h1>

                        <h3>Operazioni disponibili</h3>

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
                                        <a href="" class="text-white"><i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Registra Oggetto ad un Evento</a>
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
        <script src="./js/offcanvas.js"></script>

    </body>
</html>