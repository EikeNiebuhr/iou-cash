<?php
/**
 * @var \Laravel\Lumen\Routing\Router $router
 */

$router->get( '/', 'ApiController@index' );
$router->get( '{amount}', 'ApiController@convert' );
$router->get( '{timestamp}/{amount}', 'ApiController@convertByTimestamp' );
$router->get( '{timestampFrom}/{timestampTo}/{amount}', 'ApiController@convertInRange' );
