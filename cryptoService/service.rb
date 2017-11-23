require 'sinatra'
require 'open-uri'
require 'json'

get '/:amount' do |amount|
  val = open("https://blockchain.info/tobtc?currency=EUR&value=#{amount}").read
  response = {
   'bitcoin' => {
      'value' => val,
      'time' => Time.now.to_i   
    }
  }
  JSON[response]
end
