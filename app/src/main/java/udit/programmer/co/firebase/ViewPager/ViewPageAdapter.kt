package udit.programmer.co.firebase.ViewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    var list = arrayListOf<Fragment>()

    fun add(fragment: Fragment) {
        list.add(fragment)
    }

    override fun getItem(position: Int): Fragment = list.elementAt(position)

    override fun getCount(): Int = list.size
}