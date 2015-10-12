#!/bin/bash
find -type f -exec cat {} \; | wc -l
