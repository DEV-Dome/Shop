<?php

$curl = curl_init();

curl_setopt_array($curl, array(
    CURLOPT_URL => 'https://api.mineskin.org/v2/skins?size=128',
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_ENCODING => '',
    CURLOPT_MAXREDIRS => 10,
    CURLOPT_TIMEOUT => 0,
    CURLOPT_FOLLOWLOCATION => true,
    CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
    CURLOPT_CUSTOMREQUEST => 'GET',
    CURLOPT_HTTPHEADER => array(
        'Accept: application/json',
        'Authorization: Bearer 40a063af9d6872a7d03712347c48b4baeb2e9306360b17a4f7ff8607bc54af3e'
    ),
));

$response = json_decode(curl_exec($curl), true);
curl_close($curl);

$port = "3306";
$host = "localhost";
$username = "shopy";
$password = "Ai3tAjdE!R]n_80D";
$datenbank = "shopy";

try{
    $pdo = new PDO("mysql:host=$host;dbname=$datenbank", $username, $password);
    echo "Verbindung aufgebaut! <br>";
} catch(\mysql_xdevapi\Exception $e){
    echo $e;
    exit();
}

foreach ($response["skins"] as $skins){
    echo "Durchlauf <br/>";
    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_URL => 'https://api.mineskin.org/v2/skins/' . $skins["uuid"],
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
        CURLOPT_HTTPHEADER => array(
            'Accept: application/json',
            'Authorization: Bearer 40a063af9d6872a7d03712347c48b4baeb2e9306360b17a4f7ff8607bc54af3e'
        ),
    ));

    $responseKind = json_decode(curl_exec($curl), true);
    try{
    $sth = $pdo->prepare("INSERT INTO kunden_skins (UUID, value, signature) VALUE (?, ?, ?)");

    $sth->bindParam(1, $responseKind["skin"]["uuid"]);
    $sth->bindParam(2, $responseKind["skin"]["texture"]["data"]["value"]);
    $sth->bindParam(3, $responseKind["skin"]["texture"]["data"]["signature"]);
    $sth->execute();

    } catch(\mysql_xdevapi\Exception $e){
        echo "Fehler: ". $e;
        exit();
    }

    curl_close($curl);
}
echo "abgeschlossen!";

?>


