<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Crea criterio</title>

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
                    Boolean login = (Boolean) session.getAttribute("loginAdmin");
                    if (login != null && login.booleanValue()) {
                %>
                <section>
                    <div class="container-fluid">
                        <h1 class="text-center">Benvenuto amministratore!</h1>
                        <h2 class="text-center">Creazione criterio</h2>
                        
                        <div class="form">
                            <form method="POST" action="CreaCriterio">
                                <div class="form-group row">
                                    <label for="nomeCriterio" class="col-sm-4 col-form-label">Nome criterio</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="nomeCriterio" id="nomeCriterio" placeholder="Nome criterio">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="maxPunteggio" class="col-sm-4 col-form-label">Max punteggio (default = 10)</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="maxPunteggio" id="punteggio" value="10" placeholder="Max punteggio">
                                    </div>
                                </div>

                                <button>Crea!</button>
                            </form>
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
                                    <form method="POST" action="DashboardAdmin">
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
        <script src="./js/offcanvas.js"></script>

    </body>
</html>