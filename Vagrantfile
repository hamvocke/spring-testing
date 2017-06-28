# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"

  # Port forwardings (private)
  config.vm.network "forwarded_port", guest: 80, host: 8080, host_ip: "127.0.0.1" # Spring Boot
  config.vm.network "forwarded_port", guest: 5432, host: 15432, host_ip: "127.0.0.1" # Postgres

  # Provisioning
  config.vm.provision "shell", path: "./provisioning/jdk.sh"
  config.vm.provision "shell", path: "./provisioning/docker.sh"
  config.vm.provision "shell", path: "./provisioning/postgres.sh"
end
