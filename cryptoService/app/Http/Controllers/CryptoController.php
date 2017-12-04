<?php

namespace App\Http\Controllers;

class CryptoController extends Controller
{
  public function convert( $amount )
  {
    $btc = file_get_contents( "https://blockchain.info/tobtc?currency=EUR&value=$amount" );

    return response()->json( [
      'bitcoin' => [
        'value' => $btc,
        'time'  => time(),
      ],
    ] );
  }

  public function convertByDate( $timestamp, $amount )
  {
    $date = date( 'Y-m-d', $timestamp );

    $bpi = file_get_contents( "https://api.coindesk.com/v1/bpi/historical/close.json?start=$date&end=$date&currency=EUR" );
    $bpi = json_decode( $bpi )->bpi;
    $btc = $amount / $bpi->$date;

    return response()->json( [
      'bitcoin' => [
        'value' => $btc,
        'time'  => $timestamp,
      ],
    ] );
  }

  public function convertInRange( $timestampFrom, $timestampTo, $amount )
  {
    $dateFrom = date( 'Y-m-d', $timestampFrom );
    $dateTo = date( 'Y-m-d', $timestampTo );

    $bpi = file_get_contents( "https://api.coindesk.com/v1/bpi/historical/close.json?start=$dateFrom&end=$dateTo&currency=EUR" );
    $bpi = json_decode( $bpi, true )['bpi'];

    $btcRange = [];
    foreach( $bpi as $date => $btcPrice ) {
      $btcRange[] = [
        'time'  => \DateTime::createFromFormat( 'Y-m-d', $date )->getTimestamp(),
        'value' => $amount / $btcPrice,
      ];
    }

    return response()->json( [
      'bitcoin' => $btcRange,
    ] );
  }
}
