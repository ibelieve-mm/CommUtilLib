#!/bin/zsh

if [ -f ~/.zshrc ]; then
  #  source ~/.zshrc
  echo 存在11
else
  #  source ~/.bash_profile
  echo 不存在
fi

#a=3
#b=2
#
#c=$(expr $a + $b)
#echo "+ $c"
#
#c=$(expr $a - $b)
#echo "- $c"
#
#c=$(expr $a \* $b)
#echo "* $c"
#
#c=$(expr $a / $b)
#echo "/ $c"
#
#c=$(expr $a % $b)
#echo "% $c"
#
#if [ $a = $b ]; then
#  echo "123"
#else
#  echo "111"
#fi
#
#if [ $a != $b ]; then
#  echo "!="
#fi
#
#if [ $a != $b ]; then
#  a=188
#else
#  a=100
#fi
#
#echo $a