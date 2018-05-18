#!/bin/bash

./db/limpiarBDD.sh

gradle clean

gradle bootRun
