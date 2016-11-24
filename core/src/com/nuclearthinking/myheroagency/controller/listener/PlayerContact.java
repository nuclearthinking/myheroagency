package com.nuclearthinking.myheroagency.controller.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import lombok.NonNull;
import lombok.val;

/**
 * Created by mkuksin on 18.11.2016.
 */
public class PlayerContact implements ContactListener {

    //Срабатывает, когда два объекта начинают накладываться.
    @Override
    public void beginContact(Contact contact) {
        @NonNull val fa = contact.getFixtureA();
        @NonNull val fb = contact.getFixtureB();

        if(fa.getUserData().equals("player")) {
            System.out.println(fb.getUserData());
        }
    }

    //Срабатывает, когда два объекта прекращают соприкасаться.
    //Может быть вызван, когда тело разрушено, таким образом, это событие может иметь место вне временного шага.
    @Override
    public void endContact(Contact contact) {

    }

    //Срабатывает после обнаружения столкновения, но перед его обработкой.
    //Это позволяет нам как-то изменить контакт до его обработки. Например, можно сделать контакт неактивным.
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    //Метод позволяет осуществить логику игры, которая изменяет физику после контакта. Например, деформировать или уничтожить объект после контакта.
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
