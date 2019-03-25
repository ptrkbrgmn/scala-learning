#!/usr/bin/env bash

# Pull from ptrkbrgmn
# git pull ptrkbrgmn master

# Add git credential helper on Fedora:
# git config --global credential.helper /usr/libexec/git-core/git-credential-libsecret
#
# Add git credential helper on Ubuntu:
# git config --global credential.helper /usr/share/doc/git/contrib/credential/libsecret/git-credential-libsecret
# (However I this won't work. I don't know why)
#
# kpb	        https://karlpatrikbergman@github.com/karlpatrikbergman/scala-learning.git
# ptrkbrgmn	    https://ptrkbrgmn@github.com/ptrkbrgmn/scala-learning.git

push_ptrkbrgmn() {
  git push -u ptrkbrgmn --all
  git push ptrkbrgmn --tags
}

push_karlpatrikbergman() {
  git push -u kpb --all
  git push kpb --tags
}

push_ptrkbrgmn
push_karlpatrikbergman