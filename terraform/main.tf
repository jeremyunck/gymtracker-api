provider "aws" {
  region = "us-east-2"
}

# Create S3 bucket for application artifacts
resource "aws_s3_bucket" "gym_api_bucket" {
  bucket = "gym-api-app-artifact-bucket"
}

# Create IAM role for Beanstalk EC2 instances
resource "aws_iam_role" "beanstalk_instance_role" {
  name = "aws-gym-api-elasticbeanstalk-ec2-role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

# Attach AmazonS3FullAccess policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_role_policy_attachment" {
  role = aws_iam_role.beanstalk_instance_role.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3FullAccess"
}

# Attach AWSElasticBeanstalkWebTier policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_role_policy_attachment2" {
  role = aws_iam_role.beanstalk_instance_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

# Attach AWSElasticBeanstalkMulticontainerDocker policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_role_policy_attachment3" {
  role = aws_iam_role.beanstalk_instance_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkMulticontainerDocker"
}

# Attach AWSElasticBeanstalkWorkerTier policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_role_policy_attachment4" {
  role = aws_iam_role.beanstalk_instance_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWorkerTier"
}

# Create Elastic Beanstalk application
resource "aws_elastic_beanstalk_application" "gym_api_app" {
  name = "gym-api-app" # Replace with your desired application name
  description = "This is my Beanstalk application"
}

# Create IAM instance profile for Beanstalk EC2 instances
resource "aws_iam_instance_profile" "beanstalk_instance_profile" {
  name = "aws-gym-api-elasticbeanstalk-ec2-role"
  role = aws_iam_role.beanstalk_instance_role.name
}

# Attach AmazonS3FullAccess policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_profile_policy_attachment" {
  role = aws_iam_instance_profile.beanstalk_instance_profile.name
  policy_arn = "arn:aws:iam::aws:policy/AmazonS3FullAccess"
}

# Attach AWSElasticBeanstalkWebTier policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_profile_policy_attachment2" {
  role = aws_iam_instance_profile.beanstalk_instance_profile.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

# Attach AWSElasticBeanstalkMulticontainerDocker policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_profile_policy_attachment3" {
  role = aws_iam_instance_profile.beanstalk_instance_profile.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkMulticontainerDocker"
}

# Attach AWSElasticBeanstalkWorkerTier policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_instance_profile_policy_attachment4" {
  role = aws_iam_instance_profile.beanstalk_instance_profile.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWorkerTier"
}

# Create Beanstalk environment for the application
resource "aws_elastic_beanstalk_environment" "gym_api_app_env" {
  name = "gym-api-app-env" # Replace with your desired environment name
  application = aws_elastic_beanstalk_application.gym_api_app.name
  solution_stack_name = "64bit Amazon Linux 2023 v4.1.1 running Corretto 17"
#  set service role
    setting {
        namespace = "aws:elasticbeanstalk:environment"
        name = "ServiceRole"
        value = aws_iam_role.beanstalk_instance_role.name
    }
    setting {
        namespace = "aws:autoscaling:launchconfiguration"
        name = "IamInstanceProfile"
        value = aws_iam_role.beanstalk_instance_role.name
    }
    setting {
        namespace = "aws:autoscaling:launchconfiguration"
        name = "InstanceType"
        value = "t2.micro"
    }
}


