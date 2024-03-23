## Creating the virtual machine

    docker-machine create --driver virtualbox vm-docker

# Pointing docker to the VM

    eval $(docker-machine env vm-docker)

# Checking which machine is active

    docker-machine active

# Getting VM information

    docker-machine inspect vm-docker

# Running a Docker container

    docker run -d --name quake-log-parser -p 8080:8080 nenodes/quake-log-parser:V2


# Accessing the container

    curl http://localhost:8080 # It will give an error, as the container is not running on our machine but on the VM
    
    docker-machine ip vm-docker

    curl http://<IP-VM>:8080


# Connecting via SSH to the VM

    docker-machine ssh vm-docker

# Pointing docker to our host

    eval $(docker-machine env -u)

# Deleting a machine

    docker-machine rm -f vm-docker