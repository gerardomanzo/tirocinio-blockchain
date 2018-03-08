<%@page import="bean.Partecipazione"%>
<%@page import="bean.Oggetto"%>
<%@page import="bean.Criterio"%>
<%@page import="bean.Voto"%>
<%@page import="bean.Utente"%>
<%@page import="bean.Evento"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Visualizza i Risultati</title>

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
                        Utente utente = (Utente) session.getAttribute("utente");
                        if (utente != null) {
                    %>
                    <a href="Logout" style="color: white"> <i class="fa fa-power-off fa-3x float-xs-right"></i> </a>


                </nav>
            </header>
            <!-- /Header -->

            <!-- Main -->

            <section>
                <div class="container-fluid">
                    <h1 class="text-center">Benvenuto  <%=utente.getNomeUtente()%>!</h1>
                    <h2 class="text-center">Risultati Votazione</h2>
                    <div class="col-md-8 offset-md-2">
                        <%
                            List<Voto> lista = (List<Voto>) session.getAttribute("voti");
                            List<Criterio> criteri = (List<Criterio>) session.getAttribute("criteri");
                            List<Partecipazione> partecipazioni = (List<Partecipazione>) session.getAttribute("partecipazioni");
                            List<Oggetto> oggetti = (List<Oggetto>) session.getAttribute("oggetti");

                            if (lista != null && lista.size() > 0) {
                        %>
                        <table class="table table-bordered">
                            <thead class="thead-blue">
                                <tr>
                                    <th>Nome Candidatura</th>
                                        <%
                                            for (Criterio c : criteri) {
                                        %>
                                    <th><%=c.getNomeCriterio()%></th>
                                        <%
                                            }
                                        %>
                                    <th>Totale</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (int i = 0; i < lista.size(); i += criteri.size()) {
                                        Integer tot = 0;
                                        Boolean trovato = false;
                                %>
                                <tr>
                                    <%
                                        for (int j = 0; j < partecipazioni.size() && !trovato; j++) {
                                            for (int k = 0; k < oggetti.size() && !trovato; k++) {
                                                if (lista.get(i).getPartecipazione().contains(partecipazioni.get(j).getIdPartecipazione()) && partecipazioni.get(j).getIdOggetto().contains(oggetti.get(k).getIdOggetto())) {
                                                    trovato = true;
                                    %>
                                    <td><%=oggetti.get(k).getNomeOggetto()%></td>
                                    <%          }
                                            }
                                        }
                                    %>
                                    <%
                                        for (Criterio c : criteri) {
                                            for (int j = 0; j < criteri.size(); j++) {
                                                if (lista.get(i + j).getCriterio().contains(c.getIdCriterio())) {
                                                    tot += lista.get(i + j).getPunteggio();
                                    %>
                                    <td><%=lista.get(i + j).getPunteggio()%></td>
                                    <%
                                                }
                                            }
                                        }
                                    %>
                                    <td><%=tot%></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                        <a href="RisultatiTotali">Visualizza i Totali</a><br>
                        <% } else { %>
                        <p> Non ci sono voti al momento </p>
                        <% } %>
                        <a href="dashboardUtente.jsp">Torna alla dashboard</a>
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
                            <div class="login-form">
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