terraform {
  backend "s3" {
    bucket = "lampoil-terraform-state"
    key    = "gym-api/terraform.tfstate"
    region = "us-east-2"
  }
}