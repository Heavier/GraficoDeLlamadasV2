
(function (){
    window.addEventListener("load", function(){
                    var chart = new CanvasJS.Chart("chartContainer", {
                    theme: "theme2", //theme1
                            title: {
                            text: "Estadísticas"
                            },
                            animationEnabled: true, // change to true
                            data: [
                            {
                            // Change type to "bar", "area", "spline", "pie",etc.
                            type: "doughnut",
                                    dataPoints: [
                                    {label: "Lunes", y: MainActivity.lunes()},
                                    {label: "Martes", y: MainActivity.martes()},
                                    {label: "Miercoles", y: MainActivity.miercoles()},
                                    {label: "Jueves", y: MainActivity.jueves()},
                                    {label: "Viernes", y: MainActivity.viernes()}
                                    ]
                            }
                            ]
                        });
                            chart.render();
                    });
})();
