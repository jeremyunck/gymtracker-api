provider "aws" {
  region = "us-east-2"
}

resource "aws_ecr_repository" "gym_api_repository" {
  name = "gym_api_repository"
}

# Create a new iam role for beanstalk
resource "aws_iam_role" "beanstalk_role" {
  name = "beanstalk_role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "elasticbeanstalk.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

# Attach the policy to the role
resource "aws_iam_role_policy_attachment" "beanstalk_policy" {
  role = aws_iam_role.beanstalk_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkFullAccess"
}

# Create a new beanstalk application
resource "aws_elastic_beanstalk_application" "gym_api_application" {
  name = "gym_api_application"
}

# Create a new beanstalk environment
resource "aws_elastic_beanstalk_environment" "gym_api_environment" {
  name                = "gym_api_dev_environment"
  application         = aws_elastic_beanstalk_application.gym_api_application.name
  solution_stack_name = "64bit Amazon Linux 2018.03 v2.11.2 running Docker 18.06.1-ce"
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = aws_iam_role.beanstalk_role.name
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "EnvironmentType"
    value     = "SingleInstance"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "ServiceRole"
    value     = "aws-elasticbeanstalk-service-role"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerType"
    value     = "application"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerPortProtocol"
    value     = "80"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerHTTPSPortProtocol"
    value     = "443"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerHTTPSPort"
    value     = "443"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerPort"
    value     = "80"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerIdleTimeout"
    value     = "60"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerCrossZone"
    value     = "true"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerConnectionDrainingEnabled"
    value     = "true"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "LoadBalancerConnectionDrainingTimeout"
    value     = "60"
  }
}


