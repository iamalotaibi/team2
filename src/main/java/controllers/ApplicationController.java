/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import controllers.UI;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;
import ninja.params.PathParam;

@Singleton
public class ApplicationController {

    public UI ui;
    public Result index() {
        return Results.html().template("views/AcesUp/AcesUp.flt.html");
    }

    public Result initGame() {
        // Pass 0 or 1 depending on the game played
        this.ui = new UI(1);
        return Results.json().render(this.ui);
    }

    public Result dealClicked(Context context) {
        if (context.getRequestPath().contains("deal")) {
            this.ui.doOnDeal();
        }
        return Results.json().render(this.ui);
    }

    public Result cardClicked(Context context, @PathParam("column") int column, @PathParam("row") int row) {
        this.ui.doOnCardClicked(column, row);
        return Results.json().render(this.ui);
    }
}
