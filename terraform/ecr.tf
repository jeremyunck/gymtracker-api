provider "aws" {
  region = "us-east-2"
}

resource "aws_ecr_repository" "gym_api_repository" {
  name = "gym_api_repository"
}