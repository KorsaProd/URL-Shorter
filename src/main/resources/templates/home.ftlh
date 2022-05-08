<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home Page</title>
</head>
<body>
<h1 style="text-align: center">Short URL Builder</h1>
<br>
<h2 style="text-align: center">Сгенерировать ссылку</h2>
<form style="text-align: center" method="post" action="/home">
    <label> Вставьте оригинальный URL -
        <input
                name="originalUrl"
                placeholder="Оригинальная ссылка"
                required="required">
    </label>
    <br>
    <label> Укажите желаемую длинну короткой ссылки -
        <input
                type="number"
                name="shorterLength"
                placeholder="Длинна ссылки"
                required="required">
    </label>
    <br>
    <label> Срок действия ссылки "до" -
        <input
                type="datetime-local"
                name="expiresDate"
                placeholder="Укажите дату"
                required="required">
    </label>
    <input
            type="submit"
            value="Сгенерировать">
</form>
<br>
<br>
<h2 style="text-align: center">Ваши ссылки</h2>
<table>
    <tr>
        <th>Короткая ссылка</th>
        <th>Оригинальная ссылка</th>
        <th>Дата создания</th>
        <th>Действительна до</th>
        <th>Количество переходов</th>
        <th>Действия</th>
    </tr>
    <#list shorterLinks as links>
        <tr>
        <td>
            <a href="${links.hash}">${links.hash}</a>
        </td>
        <td>
            <a href="${links.originalUrl}">${links.originalUrl}</a>
        </td>
        <td>${links.createdAt}</td>
            <td>${links.expiresDate}</td>
        <td>${links.transitionCounter}</td>
            <td>
            <form method="post" action="/home/${links.hash}/delete">
                <input type="submit" value="Удалить">
            </form>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>