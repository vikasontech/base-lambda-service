cwd=$(pwd)
mvn clean install -DskipTests
mv target/*aws.jar target/buxfer-service-lambda-aws.jar
aws s3 cp target/*-aws.jar s3://automation-code-deploy
cd ../aws-infra
terraform apply -auto-approve
cd $cwd
