package org.powerbot.script.lang;

import org.powerbot.script.methods.MethodContext;

public abstract class BasicNamedQuery<K extends Locatable & Identifiable & Nameable> extends AbstractQuery<BasicNamedQuery<K>, K>
		implements Locatable.Query<BasicNamedQuery<K>>, Identifiable.Query<BasicNamedQuery<K>>,
		Nameable.Query<BasicNamedQuery<K>> {
	public BasicNamedQuery(final MethodContext factory) {
		super(factory);
	}

	@Override
	protected BasicNamedQuery<K> getThis() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> at(Locatable l) {
		return filter(new Locatable.Matcher(l));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> within(double distance) {
		return within(ctx.players.getLocal(), distance);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> within(Locatable target, double distance) {
		return filter(new Locatable.WithinRange(target, distance));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> nearest() {
		return nearest(ctx.players.getLocal());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> nearest(Locatable target) {
		return sort(new Locatable.NearestTo(target));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(int... ids) {
		return filter(new Identifiable.Matcher(ids));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(final int[]... ids) {
		int z = 0;

		for (final int[] x : ids) {
			z += x.length;
		}

		final int[] a = new int[z];
		int i = 0;

		for (final int[] x : ids) {
			for (final int y : x) {
				a[i++] = y;
			}
		}

		return filter(new Identifiable.Matcher(a));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> id(Identifiable... identifiables) {
		return filter(new Identifiable.Matcher(identifiables));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> name(String... names) {
		return filter(new Nameable.Matcher(names));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BasicNamedQuery<K> name(Nameable... names) {
		return filter(new Nameable.Matcher(names));
	}
}
