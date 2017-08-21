<#import "spring.ftl" as spring />
<html ng-app="aisApp">
<head>

    <link href=" <@spring.url "/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<@spring.url "/css/bootstrap-theme.min.css" />" rel="stylesheet">
    <link href="<@spring.url "/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" />
    <link href="<@spring.url "/css/main.css" />" rel="stylesheet" />

    <script src="<@spring.url "/js/jquery-3.2.0.min.js" />"></script>
    <script src="<@spring.url "/js/jquery-ui/jquery-ui.min.js"  />"></script>
    <script src="<@spring.url "/js/bootstrap.min.js" />"></script>

    <script src="<@spring.url "/js/angular/angular.min.js" />"></script>
    <script src="<@spring.url "/js/angular/angular-route.min.js" />"></script>
    <script src="<@spring.url "/js/angular/angular-resource.min.js" />"></script>

    <script src="<@spring.url "/js/app/signal/signal.module.js" />"></script>
    <script src="<@spring.url "/js/app/signal/signal.service.js" />"></script>

    <script src="<@spring.url "/js/app/log/log.module.js" />"></script>
    <script src="<@spring.url "/js/app/log/log.service.js" />"></script>

    <script src="<@spring.url "/js/app/sensor/sensor.module.js" />"></script>
    <script src="<@spring.url "/js/app/sensor/sensor.service.js" />"></script>

    <script src="<@spring.url "/js/app/dictionary/dictionary.module.js" />"></script>
    <script src="<@spring.url "/js/app/dictionary/dictionary.service.js" />"></script>

    <script src="<@spring.url "/js/app/signal-list/signal-list.module.js" />"></script>
    <script src="<@spring.url "/js/app/signal-list/signal-list.component.js" />"></script>

    <script src="<@spring.url "/js/app/log-list/log-list.module.js" />"></script>
    <script src="<@spring.url "/js/app/log-list/log-list.component.js" />"></script>

    <script src="<@spring.url "/js/app/archive-signal-list/archive-signal-list.module.js" />"></script>
    <script src="<@spring.url "/js/app/archive-signal-list/archive-signal-list.component.js" />"></script>

    <script src="<@spring.url "/js/app/sensor-list/sensor-list.module.js" />"></script>
    <script src="<@spring.url "/js/app/sensor-list/sensor-list.component.js" />"></script>

    <script src="<@spring.url "/js/app/pagination/pagination.module.js" />"></script>
    <script src="<@spring.url "/js/app/pagination/pagination.component.js" />"></script>


    <script src="<@spring.url "/js/app/app.module.js" />"></script>
    <script src="<@spring.url "/js/app/app.config.js" />"></script>

    <title>АИС</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="HeaderController">
            <ul class="nav navbar-nav">
                <#--<li ng-class="{ active: isActive('/journal')}"><a href="#">Журнал событий</a></li>-->
                <#--<li ng-class="{ active: isActive('/archive')}"><a href="#!archive">Архив событий</a></li>-->
                    <li ng-class="{ active: isActive('/logs')}"><a href="#!logs">Журнал событий</a></li>
                    <li ng-class="{ active: isActive('/sensors')}"><a href="#!sensors">Работа с датчиками</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" title="Редактировать данные пользователя">
                    <img src="<@spring.url "/images/glyphicons/png/glyphicons-4-user.png"/>" />
                </a></li>
                <li><a href="<@spring.url "logout"/>">Выход</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="content-wrapper">
    <div ng-view></div>
</div>
<footer>

</footer>

<script src="<@spring.url "/js/main.js" />"></script>
</body>
</html>