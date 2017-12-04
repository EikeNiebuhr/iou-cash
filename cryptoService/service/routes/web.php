<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/{amount}', function ($amount) use ($router) {
    $btc = file_get_contents("https://blockchain.info/tobtc?currency=EUR&value=$amount");
    return json_encode([
      'bitcoin' => [
        'value' => $btc,
	'time' => time()
      ]
    ]);
});
