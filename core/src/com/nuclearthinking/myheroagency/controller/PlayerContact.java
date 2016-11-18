package com.nuclearthinking.myheroagency.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import lombok.val;

/**
 * Created by mkuksin on 18.11.2016.
 */
public class PlayerContact implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        val fa = contact.getFixtureA();

        if(fa.getUserData() != null && fa.getUserData().equals("player")) {
            System.out.println("Player Contact");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
