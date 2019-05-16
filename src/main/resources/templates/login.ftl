<#import "spring.ftl" as spring />
<html>
<head>

    <link href=" <@spring.url "/css/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<@spring.url "/css/bootstrap-theme.min.css" />" rel="stylesheet">
    <link href="<@spring.url "/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet" />
    <link href="<@spring.url "/css/main.css" />" rel="stylesheet" />

    <script src="<@spring.url "/js/jquery-3.2.0.min.js" />"></script>
    <script src="<@spring.url "/js/jquery-ui/jquery-ui.min.js"  />"></script>

    <title>Авторизация в АИС</title>
</head>
<body style="height: auto;">
    <div class="container-login">
        <div class="row">
            <div id="content" class="well">
                <legend>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="logo-container">
                                <img src="<@spring.url "/images/logo-main.jpg" />" width="100%">
                            </div>
                        </div>
                        <div class="col-xs-8">
                            Добро пожаловать в Автоматизированную систему опроса КИА Чиркейская ГАЭС
                        </div>
                    </div>
                </legend>
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" name="f" action="<@spring.url "/login"/>" method="post">
                            <h3 style="margin-top: 0;">Вход в систему</h3>
                            <#if SPRING_SECURITY_LAST_EXCEPTION?? && SPRING_SECURITY_LAST_EXCEPTION.message??>
                                <div class="alert alert-danger">
                                    Неправильный логин или пароль.
                                </div>
                            </#if>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input class='form-control' type="text" id="username"
                                           name="username" tabindex=1 placeholder="Логин"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input class='form-control' type="password" id="password"
                                           name="password" tabindex=2 placeholder="Пароль"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-xs-12 text-right">
                                    <button type="submit" class="btn btn-primary" tabindex=3>Войти</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
