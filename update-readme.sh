#!/bin/sh

if git status -s | grep README.md; then
    git config user.email "travis@travis.com"
    git config user.name "travis"
    git add -u
    git commit -m "Update README.md"
    git push "https://${GH_TOKEN}@github.com/NomadBlacky/scala-samples.git" master:master
fi
