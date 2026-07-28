package net.celestene.someflyingmod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class HammerParticles extends TextureSheetParticle {
    protected HammerParticles(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet,
                              double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        // this.friction = 0.8F;
        // this.quadSize *= 0.85F;
        this.lifetime = 20;
        this.gCol = 1f;
        this.rCol = 0f;
        this.bCol = 0f;

        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType pType, ClientLevel pLevel,
                                                 double pX, double pY, double pZ, double pXSpeed,
                                                 double pYSpeed, double pZSpeed) {

            return new HammerParticles(pLevel, pX, pY, pZ, this.sprites, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
