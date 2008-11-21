$:.unshift(File.dirname(__FILE__) + '/../lib')
 
require 'rubygems'
require 'spec'
require 'active_record'

require File.dirname(__FILE__) + '/../init.rb'
 
require 'active_record/fixtures'
 
config = YAML::load(IO.read(File.dirname(__FILE__) + '/config/database.yml'))
ActiveRecord::Base.logger = Logger.new(File.dirname(__FILE__) + "/debug.log")
ActiveRecord::Base.establish_connection(config[ENV['DB'] || 'mysql'])
 
load(File.dirname(__FILE__) + "/config/schema.rb")
 
fixture_path = $LOAD_PATH.unshift(File.join(File.dirname(__FILE__),"fixtures"))
 
# load model
require File.join(File.dirname(__FILE__), 'models/ooga')
 
Spec::Runner.configure do |config|
end