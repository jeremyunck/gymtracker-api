terraform {
  backend "s3" {
    bucket = "terraform-state"
    key    = "gym-api/terraform.tfstate"
  }
}