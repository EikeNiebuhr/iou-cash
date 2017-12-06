<?php

namespace App\Http\Controllers;

use EllipseSynergie\ApiResponse\Contracts\Response;

class ApiController extends Controller
{

  public function __construct( Response $response )
  {
    $this->response = $response;
  }

  public function index()
  {
    return 'Hi there.';
  }

  public function convert( $amount )
  {
    if( !is_numeric( $amount ) ) {
      return $this->response->errorWrongArgs( 'parameter should be numeric' );
    }
    try {
      $btc = file_get_contents( "https://blockchain.info/tobtc?currency=EUR&value=$amount" );

      return $this->response->withArray( [
        'bitcoin' => [
          'value' => (float)$btc,
          'time'  => time(),
        ],
      ] );
    } catch( \Exception $e ) {
      return $this->response->errorInternalError( 'please contact the support' );
    }
  }

  public function convertByDate( $timestamp, $amount )
  {
    try {
      $date = new \DateTime();
      try {
        $date->setTimestamp( $timestamp );
      } catch( \Exception $e ) {
        return $this->response->errorWrongArgs( 'first parameter should be numeric' );
      }
      try {
        $bpi = file_get_contents( "https://api.coindesk.com/v1/bpi/historical/close.json?start={$date->format('Y-m-d')}&end={$date->format('Y-m-d')}&currency=EUR" );
      } catch( \Exception $e ) {
        return $this->response->errorWrongArgs( 'the requested timestamp is not processable' );
      }
      $bpi = json_decode( $bpi )->bpi;

      if( !is_numeric( $amount ) ) {
        return $this->response->errorWrongArgs( 'second parameter should be numeric' );
      }
      $btc = $amount / $bpi->{$date->format( 'Y-m-d' )};

      return $this->response->withArray( [
        'bitcoin' => [
          'value' => $btc,
          'time'  => (int)$timestamp,
        ],
      ] );
    } catch( \Exception $e ) {
      return $this->response->errorInternalError( 'please contact the support' );
    }
  }

  public function convertInRange( $timestampFrom, $timestampTo, $amount )
  {
    try {
      $dateFrom = new \DateTime();
      try {
        $dateFrom->setTimestamp( $timestampFrom );
      } catch( \Exception $e ) {
        return $this->response->errorWrongArgs( 'first parameter should be numeric' );
      }
      $dateTo = new \DateTime();
      try {
        $dateTo->setTimestamp( $timestampTo );
      } catch( \Exception $e ) {
        return $this->response->errorWrongArgs( 'second parameter should be numeric' );
      }

      try {
        $bpi = file_get_contents( "https://api.coindesk.com/v1/bpi/historical/close.json?start={$dateFrom->format('Y-m-d')}&end={$dateTo->format('Y-m-d')}&currency=EUR" );
      } catch( \Exception $e ) {
        return $this->response->errorWrongArgs( 'the requested timestamps are not processable' );
      }
      $bpi = json_decode( $bpi, true )['bpi'];

      if( !is_numeric( $amount ) ) {
        return $this->response->errorWrongArgs( 'third parameter should be numeric' );
      }

      $btcRange = [];
      foreach( $bpi as $date => $btcPrice ) {
        $btcRange[] = [
          'value' => $amount / $btcPrice,
          'time'  => \DateTime::createFromFormat( 'Y-m-d', $date )->getTimestamp(),
        ];
      }

      return $this->response->withArray( [
        'bitcoin' => $btcRange,
      ] );
    } catch( \Exception $e ) {
      return $this->response->errorInternalError( 'please contact the support' );
    }
  }
}
