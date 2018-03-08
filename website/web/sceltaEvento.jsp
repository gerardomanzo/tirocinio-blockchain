<%@page import="bean.Evento"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Associa criterio a votazione</title>

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
            <!-- Main -->
            <%

                if (login != null && login.booleanValue()) {
            %>
            <section>
                <div class="container-fluid">
                    <h1 class="text-center">Benvenuto amministratore!</h1>
                    <h2 class="text-center">Associa Criterio a Votazione</h2>
                    <div class="col-md-8 offset-md-2">
                        <form action="AssociaCriterio" method="POST">
                            <table class="table table-bordered">
                                <thead class="thead-blue">
                                    <tr >
                                        <th>ID</th>
                                        <th>Nome votazione</th>
                                        <th>Data inizio votazione</th>
                                        <th>Data fine votazione</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        List<Evento> lista = (List<Evento>) session.getAttribute("eventi");
                                        if (lista != null && lista.size() > 0) {
                                            for (Evento e : lista) {
                                    %>
                                    <tr>
                                        <td><%=e.getIdEvento()%></td>
                                        <td><%=e.getNomeEvento()%></td>
                                        <td><%=e.getDataInizio().substring(0, 10)%></td>
                                        <td><%=e.getDataFine().substring(0, 10)%></td>
                                        <td>
                                            <input type="radio" name="idEvento" value="<%=e.getIdEvento()%>">
                                        </td>
                                    </tr>
                                    <%
                                        }
                                    %>

                                    <%
                                    } else {
                                    %>
                                    <tr>
                                        <td colspan="5">Nessuna votazione registrata!</td>
                                    </tr>
                                    <%
                                        }
                                    %>    
                                </tbody>

                            </table>

                            <input type="submit" value="Avanti">
                        </form>

                        <a href="dashboardAdmin.jsp">Torna alla dashboard</a>
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