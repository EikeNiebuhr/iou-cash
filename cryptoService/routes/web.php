<?php
/**
 * @var \Laravel\Lumen\Routing\Router $router
 */

$router->get( '/', 'CryptoController@index' );
$router->get( '{amount}', 'CryptoController@convert' );
$router->get( '{timestamp}/{amount}', 'CryptoController@convertByDate' );
$router->get( '{timestampFrom}/{timestampTo}/{amount}', 'CryptoController@convertInRange' );
