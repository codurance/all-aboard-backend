# All Aboard Backend
This is the repo that contains the code for the backend of All Aboard project for the Codurance Academy 2020 September edition.

## Build status
![All aboard API - CI](https://github.com/codurance/all-aboard-backend/workflows/All%20aboard%20API%20-%20CI/badge.svg?branch=main)

## Infrastructure as code (Provisioning, tear down)
This project uses GitHub Actions to provision and tear down environments where the application can be deployed to.
They can be triggered with HTTP requests.
 
**These requests need to be done with a GitHub token.**  Please refer to this [link](https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token) to create one. The token should be from an account with permissions to access this repo.
 
### Trigger Provisioning action
Please, see [provisioning.yml](./.github/workflows/provisioning.yml) to view this action's details.
```shell script
curl --location --request POST 'https://api.github.com/repos/codurance/all-aboard-backend/dispatches' \
--header 'Accept: application/vnd.github.everest-preview+json' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer YOUR_GITHUB_TOKEN_HERE' \
--data-raw '{
    "event_type": "provisioning"
}'
```

### Trigger Tear Down action
Please, see [destroy.yml](./.github/workflows/destroy.yml) to view this action's details.

```shell script
curl --location --request POST 'https://api.github.com/repos/codurance/all-aboard-backend/dispatches' \
--header 'Accept: application/vnd.github.everest-preview+json' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer YOUR_GITHUB_TOKEN_HERE' \
--data-raw '{
    "event_type": "destroy"
}'
```

## CI/CD
This is executed with GitHub Actions, too. Please, see [ci.yml](./.github/workflows/ci.yml) to view this action's details.
